package com.example.capstoneproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "HealthData.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "UserHealth"

        // Column Names
        private const val COL_ID = "id"
        private const val COL_USERNAME = "username"
        private const val COL_EMAIL = "email"
        private const val COL_PASSWORD = "password"
        private const val COL_FULLNAME = "fullname"
        private const val COL_AGE = "age"
        private const val COL_GENDER = "gender"
        private const val COL_HEIGHT = "height"
        private const val COL_WEIGHT = "weight"
        private const val COL_BMI = "bmi"
        private const val COL_DIET_TYPE = "diet_type"
        private const val COL_BP = "bp"
        private const val COL_HR = "hr"
        private const val COL_BS = "bs"
        private const val COL_OS = "os"
        private const val COL_BT = "bt"
        private const val COL_PA = "physical_activity"
        private const val COL_SH = "sleep_hours"
        private const val COL_CH = "cholesterol"
        private const val COL_STRESS = "stress_level"
        private const val COL_SMOKE = "smoke"
        private const val COL_DRINK = "drink"
        private const val COL_FAMILY_HISTORY = "family_history"
        private const val COL_RECENT_ILLNESS = "recent_illness"
        private const val COL_PREEXISTING_CONDITIONS = "preexisting_conditions"
        private const val COL_PROFILE_IMAGE = "profile_image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_USERNAME TEXT,
                $COL_EMAIL TEXT,
                $COL_PASSWORD TEXT,
                $COL_FULLNAME TEXT,
                $COL_AGE INTEGER,
                $COL_GENDER TEXT,
                $COL_HEIGHT REAL,
                $COL_WEIGHT REAL,
                $COL_BMI REAL,
                $COL_DIET_TYPE TEXT,
                $COL_BP TEXT,
                $COL_HR TEXT,
                $COL_BS TEXT,
                $COL_OS TEXT,
                $COL_BT TEXT,
                $COL_PA TEXT,
                $COL_SH TEXT,
                $COL_CH TEXT,
                $COL_STRESS TEXT,
                $COL_SMOKE TEXT,
                $COL_DRINK TEXT,
                $COL_FAMILY_HISTORY TEXT,
                $COL_RECENT_ILLNESS TEXT,
                $COL_PREEXISTING_CONDITIONS TEXT,
                $COL_PROFILE_IMAGE BLOB
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert Profile Data
    fun insertProfileData(fullName: String, profileImage: ByteArray?): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COL_FULLNAME, fullName)
            put(COL_PROFILE_IMAGE, profileImage)
        }
        db.close()
        return insertOrUpdateData(contentValues)
    }

    // Insert or Update User Data
    fun insertOrUpdateData(values: ContentValues): Boolean {
        val db = writableDatabase
        val rowId =
            db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
        return rowId != -1L
    }

    // Retrieve Data
    fun getUserData(username: String): ContentValues? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME, null, "$COL_USERNAME = ?", arrayOf(username),
            null, null, null
        )
        val values = if (cursor.moveToFirst()) {
            val contentValues = ContentValues()
            for (i in 0 until cursor.columnCount) {
                contentValues.put(cursor.getColumnName(i), cursor.getString(i))
            }
            contentValues
        } else {
            null
        }
        cursor.close()
        db.close()
        return values
    }

    // Validate user login
    fun validateUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_USERNAME = ? AND $COL_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }

    // Insert specific data functions for each activity
    fun insertBloodPressure(bp: String): Boolean {
        val values = ContentValues().apply { put(COL_BP, bp) }
        return insertOrUpdateData(values)
    }

    fun insertBloodSugar(bs: String): Boolean {
        val values = ContentValues().apply { put(COL_BS, bs) }
        return insertOrUpdateData(values)
    }

    fun insertBodyTemperature(bt: String): Boolean {
        val values = ContentValues().apply { put(COL_BT, bt) }
        return insertOrUpdateData(values)
    }

    fun insertCholesterol(cholesterol: String): Boolean {
        val values = ContentValues().apply { put(COL_CH, cholesterol) }
        return insertOrUpdateData(values)
    }

    fun insertHeartRate(hr: String): Boolean {
        val values = ContentValues().apply { put(COL_HR, hr) }
        return insertOrUpdateData(values)
    }

    fun insertOxygenSaturation(os: Int): Boolean {
        val values = ContentValues().apply { put(COL_OS, os) }
        return insertOrUpdateData(values)
    }

    fun insertPhysicalActivity(pa: String): Boolean {
        val values = ContentValues().apply { put(COL_PA, pa) }
        return insertOrUpdateData(values)
    }

    fun insertSleepHours(sh: String): Boolean {
        val values = ContentValues().apply { put(COL_SH, sh) }
        return insertOrUpdateData(values)
    }

    fun insertAge(age: Int): Boolean {
        val values = ContentValues().apply { put(COL_AGE, age) }
        return insertOrUpdateData(values)
    }

    fun insertGender(gender: String): Boolean {
        val values = ContentValues().apply { put(COL_GENDER, gender) }
        return insertOrUpdateData(values)
    }

    fun insertPreexistingCondition(condition: String): Boolean {
        val values = ContentValues().apply { put(COL_PREEXISTING_CONDITIONS, condition) }
        return insertOrUpdateData(values)
    }

    fun insertRecentIllness(illness: String): Boolean {
        val values = ContentValues().apply { put(COL_RECENT_ILLNESS, illness) }
        return insertOrUpdateData(values)
    }

    fun insertUserData(column: String, value: String): Boolean {
        val values = ContentValues().apply { put(column, value) }
        return insertOrUpdateData(values)
    }

    fun getProfileImage(username: String): ByteArray? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COL_PROFILE_IMAGE),
            "$COL_USERNAME = ?",
            arrayOf(username),
            null,
            null,
            null
        )
        var image: ByteArray? = null
        if (cursor.moveToFirst()) {
            image = cursor.getBlob(cursor.getColumnIndexOrThrow(COL_PROFILE_IMAGE))
        }
        cursor.close()
        db.close()
        return image
    }
}
