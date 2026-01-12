package com.example.stepsynch.googlefit

import android.app.Activity
import android.content.Context

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest

import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.concurrent.TimeUnit



class GoogleFitManager(private val context: Context) {

    private val fitnessOptions = FitnessOptions.builder()
        .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.TYPE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.TYPE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
        .build()

    fun hasPermissions(): Boolean {
        val account = GoogleSignIn.getAccountForExtension(context, fitnessOptions)
        return GoogleSignIn.hasPermissions(account, fitnessOptions)
    }

    fun requestPermissions(activity: Activity, requestCode: Int) {
        val account = GoogleSignIn.getAccountForExtension(context, fitnessOptions)
        GoogleSignIn.requestPermissions(
            activity,
            requestCode,
            account,
            fitnessOptions
        )
    }

    fun readTodayStats(onResult: (steps: Int, calories: Int, distance: Double) -> Unit) {
        val account = GoogleSignIn.getAccountForExtension(context, fitnessOptions)

        val calendar = Calendar.getInstance()

        // Start of today
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val start = calendar.timeInMillis

        // End of today
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val end = calendar.timeInMillis

        val request = DataReadRequest.Builder()
            .aggregate(DataType.TYPE_STEP_COUNT_DELTA)
            .aggregate(DataType.TYPE_CALORIES_EXPENDED)
            .aggregate(DataType.TYPE_DISTANCE_DELTA)
            .setTimeRange(start, end, TimeUnit.MILLISECONDS)
            .bucketByTime(1, TimeUnit.DAYS)
            .build()


        Fitness.getHistoryClient(context, account)
            .readData(request)
            .addOnSuccessListener { response ->
                var steps = 0
                var calories = 0
                var distance = 0.0

                response.buckets.flatMap { it.dataSets }.forEach { dataSet ->
                    dataSet.dataPoints.forEach { dp ->
                        when (dp.dataType) {
                            DataType.TYPE_STEP_COUNT_DELTA ->
                                steps += dp.getValue(Field.FIELD_STEPS).asInt()

                            DataType.TYPE_CALORIES_EXPENDED ->
                                calories += dp.getValue(Field.FIELD_CALORIES).asFloat().toInt()

                            DataType.TYPE_DISTANCE_DELTA ->
                                distance += dp.getValue(Field.FIELD_DISTANCE).asFloat()
                        }
                    }
                }

                onResult(steps, calories, distance)
            }
    }

    fun readThisWeekSteps(
        onResult: (dailySteps: List<Int>, totalWeek: Int, weeklyAvg: Int) -> Unit
    ) {
        val account = GoogleSignIn.getAccountForExtension(context, fitnessOptions)

        val cal = Calendar.getInstance()

        // Move calendar to start of this week (Monday 00:00)
        cal.firstDayOfWeek = Calendar.MONDAY
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis

        // End = start of next week
        cal.add(Calendar.WEEK_OF_YEAR, 1)
        val end = cal.timeInMillis

        val request = DataReadRequest.Builder()
            .aggregate(DataType.TYPE_STEP_COUNT_DELTA)
            .setTimeRange(start, end, TimeUnit.MILLISECONDS)
            .bucketByTime(1, TimeUnit.DAYS)
            .build()

        Fitness.getHistoryClient(context, account)
            .readData(request)
            .addOnSuccessListener { response ->
                // For each day bucket, sum steps
                val dailySteps = response.buckets.map { bucket ->
                    bucket.dataSets.sumOf { dataSet ->
                        dataSet.dataPoints.sumOf { dp ->
                            dp.getValue(Field.FIELD_STEPS).asInt()
                        }
                    }
                }

                val total = dailySteps.sum()
                // average over the days we actually got (avoid /0)
                val avg = if (dailySteps.isNotEmpty()) total / dailySteps.size else 0

                onResult(dailySteps, total, avg)
            }
            .addOnFailureListener {
                onResult(emptyList(), 0, 0)
            }
    }

    fun getStreakSinceInstall(
        installTimeMillis: Long,
        dailyGoal: Int,
        onResult: (streak: Int) -> Unit
    ) {
        val account = GoogleSignIn.getAccountForExtension(context, fitnessOptions)

        val request = DataReadRequest.Builder()
            .aggregate(DataType.TYPE_STEP_COUNT_DELTA)
            .bucketByTime(1, TimeUnit.DAYS)
            .setTimeRange(
                installTimeMillis,
                System.currentTimeMillis(),
                TimeUnit.MILLISECONDS
            )
            .build()

        Fitness.getHistoryClient(context, account)
            .readData(request)
            .addOnSuccessListener { response ->
                var streak = 0

                response.buckets.forEach { bucket ->
                    val stepsForDay = bucket.dataSets
                        .flatMap { it.dataPoints }
                        .sumOf {
                            it.getValue(Field.FIELD_STEPS).asInt()
                        }

                    if (stepsForDay >= dailyGoal) {
                        streak++
                    }
                }

                onResult(streak)
            }
            .addOnFailureListener {
                onResult(0)
            }
    }


}
