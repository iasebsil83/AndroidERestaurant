package fr.isen.sebastien_SILVANO.androiderestaurant.log

import android.annotation.SuppressLint
import fr.isen.sebastien_SILVANO.androiderestaurant.log.CodeInfo

class Message(info: CodeInfo) : CodeInfo(
    info.getActorName(),
    info.getFileName(),
    info.getFunctionName()
) {

    //log
    @SuppressLint("LongLogTag")
    fun log(message: String) {
        //Log.i("    UselessMessage > log() ", "A MESSAGE has occured {")
        super.log(CODEINFO__MESSAGE, message)
        //Log.i("    UselessMessage > log() ", "}")
    }
}