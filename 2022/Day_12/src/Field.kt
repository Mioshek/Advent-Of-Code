class Field(val height: Int, val width: Int) {
    val field = Array(height){Array(width){PathParticle(0,0,'0', null)} }
    var startParticle: PathParticle? = null
    var endParticle: PathParticle? = null
}