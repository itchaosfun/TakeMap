package com.chaos.takemap.util

import android.content.Context
import java.io.*
import java.util.*

object FileUtils {

    val TAG = "FileUtils"

    /**
     * 从assets目录中复制文件到本地sd卡缓存中
     * @param fileName as String
     */
    fun copyFileFromAssetsToCache(context: Context, fileName: String) {
        val assetManager = context.assets
        val file = File(context.getExternalFilesDir(null)?.absolutePath + File.separator + fileName)
        if (!file.exists()) {
            LogUtil.d(TAG, "file not exist")
            try {
                val `in` = assetManager.open(fileName)
                val out =
                    FileOutputStream(context.getExternalFilesDir(null)?.absolutePath + File.separator + fileName)
                val buffer = ByteArray(1024)
                var read = `in`.read(buffer)
                while (read != -1) {
                    out.write(buffer, 0, read)
                    read = `in`.read(buffer)
                }
            } catch (e: Exception) {
                LogUtil.d(TAG, "file copy error : " + e.message)
            }

        } else {
            LogUtil.d(TAG, "$fileName is exsit")
        }
    }

//    fun getGeoJson(context: Context): GeoJson {
//        //读取asset中的geojson文件成一个jsonString
//        val stringBuilder = StringBuilder();
//        try {
//            val inputStreamReader = context.assets.open(context.getString(R.string.hd_map_geojson));
//            val bufferedReader = BufferedReader(InputStreamReader(inputStreamReader))
//            var line: String? = null
//            while (({ line = bufferedReader.readLine();line }()) != null) {
//                stringBuilder.append(line);
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//        return Gson().fromJson(stringBuilder.toString(), GeoJson::class.java)
//    }


//    fun getMapJson(context: Context): XiaochenMapData {
//        //读取asset中的geojson文件成一个jsonString
//        val stringBuilder = StringBuilder();
//        try {
//            val inputStreamReader =
//                context.assets.open(context.getString(R.string.xiaochen_map_json));
//            val bufferedReader = BufferedReader(InputStreamReader(inputStreamReader))
//            var line: String? = null
//            var i = 0;
//            while (({ line = bufferedReader.readLine();line }()) != null) {
//                stringBuilder.append(line);
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        LogUtil.d(TAG, "MAPData = ${stringBuilder.toString()}")
//        return Gson().fromJson(stringBuilder.toString(), XiaochenMapData::class.java)
//    }


    fun writeMapData(context: Context, data: String, fileName: String = "mapdata.json") {
        LogUtil.d(TAG, "data = $data, fileName = $fileName")
        val file =
            File(context.getExternalFilesDir(null)?.absolutePath + File.separator + fileName)
        if (!file.exists()) {
            val dir = File(file.parent)
            dir.mkdirs()
            file.createNewFile()
        }
        try {
            val out = FileOutputStream(file)
            out.write(data.toByteArray())
            out.close()
            out.flush()
        } catch (e: Exception) {
            LogUtil.d(TAG, "file copy error : " + e.message)
        }
    }

    fun readMapData(context: Context): String {
        var file =
            File(context.getExternalFilesDir(null)?.absolutePath + File.separator + "calMapdata.json")
        if (!file.exists()) {
            file =
                File(context.getExternalFilesDir(null)?.absolutePath + File.separator + "mapdata.json")

            if (!file.exists()) {
                return ""
            }
        }

        try {
            val inputStream = FileInputStream(file)
            val string = String(inputStream.readBytes())
            LogUtil.d(TAG, "mapData = $string")
            return string
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    var latestFileName = "log_${Date().formatDate("yyyyMMdd_HHmmss")}.txt"

    fun writeLog(context: Context, data: String) {
        val fileName = latestFileName
        var file =
            File(context.getExternalFilesDir(null)?.absolutePath + File.separator + fileName)
        if (!file.exists()) {
            val dir = File(file.parent)
            dir.mkdirs()
            file.createNewFile()
        }

        val fileSize = file.length()
        if (fileSize >= 1024 * 1024 * 5) {
            file = File(
                context.getExternalFilesDir(null)?.absolutePath + File.separator + "log_${
                    Date().formatDate("yyyyMMdd_HHmmss")
                }.txt"
            )
            latestFileName = "log_${
                Date().formatDate("yyyyMMdd_HHmmss")
            }.txt"
            file.createNewFile()
        }

        var fileWriter: FileWriter? = null
        try {
            fileWriter = FileWriter(file, true)
            fileWriter.write("$data \n")

        } catch (e: Exception) {
            println("file copy error : " + e.message)
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close()
                } catch (e: java.lang.Exception) {
                    println("error")
                }
            }
        }
    }
}