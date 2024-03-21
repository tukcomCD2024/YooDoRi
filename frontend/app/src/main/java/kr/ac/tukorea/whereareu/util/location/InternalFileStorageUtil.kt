package kr.ac.tukorea.whereareu.util.location

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InternalFileStorageUtil(private val context: Context) {
    fun appendContentToFile(content: String) {
        val now = getCurrentTime()
        val filename = "$now.txt"

        try {
            // 파일이 존재하는지 확인
            val file = File(context.filesDir, filename)
            Log.d("ab path", file.absolutePath)
            Log.d("path", file.path)

            if (!file.exists()) {
                storeFileUsingStream(filename, content)
            } else {
                // 파일이 존재하지 않으면 새로운 파일 생성
                // 파일이 존재하면 기존 내용을 읽어온 후 내용을 추가
                val fileReader = FileReader(file)
                val bufferedReader = BufferedReader(fileReader)

                // 기존 내용을 StringBuilder에 저장
                val stringBuilder = StringBuilder()
                var line: String?

                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line).append('\n')
                }

                // 새로운 내용을 추가
                stringBuilder.append(content)

                // 파일을 열어서 기존 내용을 유지한 채로 내용을 추가
                val fileWriter = FileWriter(file)
                val bufferedWriter = BufferedWriter(fileWriter)

                // 새로운 내용을 파일에 쓰기
                bufferedWriter.write(stringBuilder.toString())

                // 리소스 정리
                bufferedWriter.close()
                fileWriter.close()
                bufferedReader.close()
                fileReader.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentTime(): String{
        Locale.setDefault(Locale.KOREA)
        val currentTime = System.currentTimeMillis()
        val date = Date(currentTime)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(date)
    }

    fun storeFileUsingStream(filename: String, content: String) {
        // API 24 이상에서, MODE_PRIVATE 사용 안하면, SecurityException 발생
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(content.toByteArray())
        }
    }

}