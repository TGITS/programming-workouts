const euclidean_distance = (x, y) => {
  return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))
}

export const solve = (x, y) => {
  const distance_from_origin = euclidean_distance(x, y);
  
  if (distance_from_origin <= 1) {
    return 10;
  }

  if (distance_from_origin > 1 && distance_from_origin <= 5) {
    return 5;
  }

  if (distance_from_origin > 5 && distance_from_origin <= 10) {
    return 1;
  }

  return 0;
};

