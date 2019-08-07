const ONE_TERA_MILLISECONDS = 1e12;
export const gigasecond = (date) => {
  return new Date(date.getTime() + ONE_TERA_MILLISECONDS)
};
