package com.rockthejvm.practice

import scala.annotation.tailrec

object TuplesMapsExercises {

  /**
   * Social network: map between person's name (string)
   * and set of friends (strings)
   * friend relationships are mutual
   *
   * add person to network
   * remove person from network
   * add friend relationship (bi-directional)
   * remove friend relationship (bi-directional)
   *
   * number of friends of person
   * who has most friends
   * how many have no friends
   * if there is a social connection between two people (direct or not)
   */

  type Person = String
  type Network = Map[Person, Set[Person]]

  def addPerson(network: Network, person: Person): Network =
    network + (person -> Set())

  def removePerson(network: Network, person: Person): Network =
    (network - person).view.mapValues(_ - person).toMap

  def friend(network: Network, a: Person, b: Person): Network =
    if (!network.contains(a))
      throw new IllegalArgumentException(s"the person $a is not in the network")
    else if (!network.contains(b))
      throw new IllegalArgumentException(s"the person $b is not in the network")
    else
      network ++ Map(
        a -> (network(a) + b),
        b -> (network(b) + a)
      )

  def unfriend(network: Network, a: Person, b: Person): Network =
    if (!network.contains(a) || !network.contains(b))
      network
    else
      network ++ Map(
        a -> (network(a) - b),
        b -> (network(b) - a)
      )

  def numFriends(network: Network, person: Person): Int =
    if (!network.contains(person)) -1
    else network(person).size

  def mostFriends(network: Network): Person =
    if (network.isEmpty)
      throw new RuntimeException("network is empty")
    else
      network.foldLeft("") { (max, curr) =>
        if (!network.contains(max) || curr._2.size > network(max).size)
          curr._1
        else
          max
      }

  def noFriends(network: Network): Set[Person] =
    network
      .view
      .filter((person, friends) => friends.isEmpty)
      .map[String]((key, _) => key)
      .toSet

  def hasConnection(network: Network, a: Person, b: Person): Boolean = {
    @tailrec
    def search(discovered: Set[Person], considered: Set[Person]): Boolean = {
      if (discovered.isEmpty) false
      else {
        val person = discovered.head
        val personFriends = network(person)
        if (personFriends.contains(b)) true
        else search(discovered - person ++ personFriends -- considered, considered + person)
      }
    }
    if (!network.contains(a) || !network.contains(b)) false
    else search(network(a), network(b))
  }

  def main(args: Array[String]): Unit = {
    val empty: Network = Map()
    val network: Network = addPerson(addPerson(empty, "Bob"), "Mary")
    assert(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary") == network)

    val people = List("Bob", "Mary", "Jim").foldLeft(empty)(addPerson)
    val simpleNet = friend(friend(people, "Bob", "Mary"), "Jim", "Mary")
    assert(List("Bob", "Mary", "Jim").map(person => numFriends(simpleNet, person)) == List(1, 2, 1))
    assert(mostFriends(simpleNet) == "Mary")
    val complexNet = addPerson(simpleNet, "John")
    assert(noFriends(complexNet) == Set("John"))
    assert(hasConnection(complexNet, "Bob", "Jim"))
    assert(!hasConnection(complexNet, "Bob", "John"))
  }
}
