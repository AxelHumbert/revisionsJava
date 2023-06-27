package fr.uge.ymca;

import java.util.*;
import java.util.stream.Collectors;

public final class House {

  private final List<People> list;
  private final HashSet<Kind> discount;
  private final HashMap<Kind, Integer> discounts;
  private static final int DISCOUNT = 80;
  private static final int VILLAGE_PEOPLE_PRICE = 100;
  private static final int MINION_PRICE = 1;

  public House() {
    this.list = new ArrayList<>();
    this.discount = new HashSet<>();
    this.discounts = new HashMap<>();
  }

  public void add(People people) {
    Objects.requireNonNull(people);
    list.add(people);
  }

  @Override
  public String toString() {
    /*
    Code for the question 2
    if (list.isEmpty()) {
      return "Empty House";
    } else {
      var sb = new StringBuilder();
      var separator = " ";
      sb.append("House with");
      for (var i = 0; i < list.size(); i++) {
        sb.append(separator).append(list.get(i).name());
        separator = ", ";
      }
      return sb.toString();
    }*/
    if (list.isEmpty()) {
      return "Empty House";
    } else {
    return "House with " + list.stream()
            .map(People::name)
            .sorted()
            .collect(Collectors.joining(", "));
    }
  }

  public Double averagePrice() {
    if (list.isEmpty()) {
      return Double.NaN;
    } else {
      return list.stream()
              .mapToDouble(this::price)
              .sum() / list.size();
    }
  }

  private double price(People people) {
    double price = 0;
    switch(people) {
      case VillagePeople villagePeople -> {
        if (discounts.containsKey(villagePeople.kind())) {
          price = VILLAGE_PEOPLE_PRICE * (100 - discounts.get(villagePeople.kind())) / 100.0;
        } else {
          price = VILLAGE_PEOPLE_PRICE;
        }
      }
      case Minion minion -> price = MINION_PRICE;
    }
    return price;
  }

  public void addDiscount(Kind kind) {
    Objects.requireNonNull(kind);
    //discount.add(kind);
    discounts.put(kind, DISCOUNT);
  }

  public void addDiscount(Kind kind, int percent) {
    Objects.requireNonNull(kind);
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException();
    }
    discounts.put(kind, percent);
  }

  public void removeDiscount(Kind kind) {
    Objects.requireNonNull(kind);
    if (!discounts.containsKey(kind)) {
      throw new IllegalStateException();
    }
    discounts.remove(kind);
  }

  private int numberPeople(Kind kind) {
    var nbPeople = 0;
    for (var people : list) {
      switch (people) {
        case VillagePeople villagePeople -> {
          if (villagePeople.kind() == kind) {
            nbPeople++;
          }
        }
        case Minion minion -> {

        }
      }
    }
    return nbPeople;
  }

  /* Mauvais calcul */
  public Map<Integer, Integer> priceByDiscount() {
    var hashMap = new HashMap<Integer, Integer>();
    var total = list.stream()
                    .mapToInt(p -> (int) price(p))
                    .sum();
    /* Problème ici */
    /*
    discounts.forEach((k, p) -> {
      hashMap.put(p, numberPeople(k) * (int) price(people));
    });*/
    /* */
    var sumMap = hashMap.values()
            .stream()
            .mapToInt(v -> v)
            .sum();
    hashMap.put(0, total - sumMap);
    return hashMap;
  }
}
