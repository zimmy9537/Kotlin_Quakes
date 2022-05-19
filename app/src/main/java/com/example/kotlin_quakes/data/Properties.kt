/* 
Copyright (c) 2022 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */
package com.example.kotlin_quakes.data

import com.google.gson.annotations.SerializedName

data class Properties (

	@SerializedName("mag") val mag : Double,
	@SerializedName("place") val place : String,
	@SerializedName("time") val time : Long,
	@SerializedName("updated") val updated : Long,
	@SerializedName("tz") val tz : String,
	@SerializedName("url") val url : String,
	@SerializedName("detail") val detail : String,
	@SerializedName("felt") val felt : String,
	@SerializedName("cdi") val cdi : String,
	@SerializedName("mmi") val mmi : Double,
	@SerializedName("alert") val alert : String,
	@SerializedName("status") val status : String,
	@SerializedName("tsunami") val tsunami : Int,
	@SerializedName("sig") val sig : Int,
	@SerializedName("net") val net : String,
	@SerializedName("code") val code : String,
	@SerializedName("ids") val ids : String,
	@SerializedName("sources") val sources : String,
	@SerializedName("types") val types : String,
	@SerializedName("nst") val nst : String,
	@SerializedName("dmin") val dmin : Double,
	@SerializedName("rms") val rms : Double,
	@SerializedName("gap") val gap : Int,
	@SerializedName("magType") val magType : String,
	@SerializedName("type") val type : String,
	@SerializedName("title") val title : String
)