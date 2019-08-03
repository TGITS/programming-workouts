const ONE_GIGASECOND = 1000000000;
export const gigasecond = (date) => {
  date.setSeconds(date.getSeconds() + ONE_GIGASECOND);
  return date;
};
