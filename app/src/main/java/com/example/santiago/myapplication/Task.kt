package com.example.santiago.myapplication

import java.io.Serializable

class Task(val id: String, var name:String, var level: Int =1, var priority: Boolean = false, var favourite: Boolean = false) : Serializable
