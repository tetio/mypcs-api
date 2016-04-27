import pdi.jwt.{Jwt, JwtAlgorithm, JwtClaim}
import spray.json

implicit def intTsdsdsdime2s(i: Int) = new {
  def times(fn: => Unit) = (1 to i) foreach (x => fn)
}

val username = "NESTA"
val nif = "B7543900"
//JwtClaim()
var claim = JwtClaim(s"""{"user": "$username"}""")
claim = claim + s"""{"nif": "$nif"}"""
claim by("PORTIC")
claim to(nif)
//claim = claim.expiresAt(System.currentTimeMillis() + 24*60*60*100L)
claim = claim.expiresAt(System.currentTimeMillis()/1000+3)
val secret = "n00bi3 is b4ck, d0n't w41t f0r oth3rs"
val alg = JwtAlgorithm.HS256



val token = Jwt.encode(claim.toJson, secret, alg)

val res = Jwt.decode(token, secret, Seq(JwtAlgorithm.HS256))

4 times {
  println(Jwt.decode(token, secret, Seq(JwtAlgorithm.HS256)))
  Thread.sleep(1000L)
}

