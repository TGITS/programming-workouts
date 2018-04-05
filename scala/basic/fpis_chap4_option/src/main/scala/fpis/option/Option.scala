package fpis.option

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B]
  def flatMap[B](f: A => Option[B]): Option[B]
  def getOrElse[B >: A](default: => B): B
  def orElse[B >: A](ob: => Option[B]): Option[B]
  def filter(f: A => Boolean): Option[A]
}

case class Some[+A](get: A) extends Option[A]{
  def map[B](f: A => B): Option[B] = Some(f(get))
  def flatMap[B](f: A => Option[B]): Option[B] = f(get)
  def getOrElse[B >: A](default: => B): B = get
  def orElse[B >: A](ob: => Option[B]): Option[B] = this
  def filter(f: A => Boolean): Option[A] = if (f(get)) this else None
}

case object None extends Option[Nothing]{
  def map[B](f: Any => B): Option[B] = None
  def flatMap[B](f: Any => Option[B]): Option[B] = None
  def getOrElse[Any](default: => Any): Any = default
  def orElse[Nothing](ob: => Option[Any]): Option[Any] = ob
  def filter(f: Nothing => Boolean): Option[Nothing] = None
}

