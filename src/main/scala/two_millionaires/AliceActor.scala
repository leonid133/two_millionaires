package two_millionaires

import akka.actor.Actor
import akka.actor._

/**
  * Created by ln.blohin on 18.01.2016.
  */
class AliceActor extends Actor {
  val n = 21 //(3*7)
  val d = 5 // secret exp e^-1 mod fi(n)
  var i = 2 // >1 Alice million
  var pp = 5 //N/2 bit

  var yu:Array[Int] = new Array[Int](10)
  var zu:Array[Int] = new Array[Int](10)

  def Da(x: Int):Double = Math.pow(x, d) % n //decrypt function

  def receive = {

    case InitMillion =>
      if(InitMillion.i > 1 && InitMillion.i <=10)
        i = InitMillion.i
      else if(InitMillion.i >10) {
        println("Alice: I'm not so rich :( I have less than 10 million!")
        context.stop(self)
      }
      else {
        println("Alice: I'm richer :) I have more than 1 million!")
        context.stop(self)
      }

    case FirstMsgBtoA =>
      print("Alice: Bob sent me a number of ")
      println(FirstMsgBtoA.k)
      print("Alice: I counted zu ")
      var count = 0
      for ( x <- yu ) {
        yu(count) = Da(FirstMsgBtoA.k + count).toInt
        zu(count) = yu(count)%pp
        if(count >= i)
          zu(count) = yu(count)%pp + 1
        print(zu(count))
        print(", ")
        count+=1
      }
      println("")
      FirstMsgAtoB.pp = pp
      FirstMsgAtoB.zu = zu
      sender ! FirstMsgAtoB

    case SecondMsgBtoA =>
      if( SecondMsgBtoA.answer )
        println( "Alice: I know i>=j ")
      else
        println( "Alice: I know i<j ")
      print("Alice: i = ")
      println(i)
      context.stop(self)

    case Stop =>
      context.stop(self)
  }
}