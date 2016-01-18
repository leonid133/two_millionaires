package two_millionaires

import akka.actor.Actor
import akka.actor._

/**
  * Created by ln.blohin on 18.01.2016.
  */
class BobActor(alice: ActorRef) extends Actor{
  val x = 19 //x rnd int < n
  val e = 5 // public exp
  val n = 21 // N bit p*q (3*7)
  var j = 6 //<10 Bob million

  def Ea(x: Int):Double = Math.pow(x, e) % n //encrypt function

  def receive = {

    case InitMillion =>
      if(InitMillion.j<10 && InitMillion.j>=1)
        j = InitMillion.j
      else if(InitMillion.j<1) {
        println("Bob: I'm a millionaire. I have at least 1 million!")
        context.stop(self)
      }
      else {
        println("Bob: I have less than 10 million!")
        context.stop(self)
      }

    case StartBA =>
      FirstMsgBtoA.k = Ea(x).toInt - j + 1
      alice ! FirstMsgBtoA

    case FirstMsgAtoB =>
      var count = 1
      for(xA <- FirstMsgAtoB.zu)
      {
        if(count == j) {
          print("Bob: x mod pp = ")
          println(x % FirstMsgAtoB.pp)
          print("Bob: zu(j) = ")
          println(xA)
          if(x % FirstMsgAtoB.pp == xA) {
            println("Bob: I know i>=j")
            SecondMsgBtoA.answer = true
          }
          else {
            println("Bob: I know i<j")
            SecondMsgBtoA.answer = false
          }
        }
        count += 1
      }
      print("Bob: j = ")
      println(j)
      sender ! SecondMsgBtoA
      context.stop(self)

    case Stop =>
      context.stop(self)
  }
}