package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/23.
  */
object Example11_5 extends App {

 class TypeVariableBound{
   def compare[T<:Comparable[T]](first:T,second:T)={
     if(first.compareTo(second)>0)
       first
     else second
   }
 }
  val tvb=new TypeVariableBound
  println(tvb.compare("A","B"))


}
