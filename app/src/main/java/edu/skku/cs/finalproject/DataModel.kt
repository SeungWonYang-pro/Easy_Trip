package edu.skku.cs.finalproject

class Country(var name: String, var cId: Int){}


data class Money(var date: String, var krw : CurInfos){
    data class CurInfos(var jpy:Double, var usd: Double, var eur: Double, var gbp: Double, var aud: Double)
}

data class Translated(var message: InterResult){
    data class InterResult(var result : FinalResult ){
        data class FinalResult(var translatedText :String)
    }
}
data class DataModel (var location: LocationState, var current: Weather){
    data class Weather(
        var temp_c: Double? = null,
        var wind_mph: Double? = null,
        var condition: Condition)
    data class Condition(var text: String?= null)
    data class LocationState(var lat: Double?= null, var lon: Double? =null)
}
