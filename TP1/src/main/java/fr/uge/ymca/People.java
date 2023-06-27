package fr.uge.ymca;

public sealed interface People permits VillagePeople, Minion {
  <R> String name();
  //int price();
}
