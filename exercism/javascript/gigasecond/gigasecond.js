const ONE_TERA_MILLISECONDS = 1e12;
export const gigasecond = (date) => {
  let datePlus1TeraMilliseconds = new Date(date.getTime() + ONE_TERA_MILLISECONDS)
  return datePlus1TeraMilliseconds;
};
