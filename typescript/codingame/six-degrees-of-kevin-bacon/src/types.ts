export interface MovieCast {
  movieName: string;
  actors: string[];
}

export interface ProblemInput {
  actorName: string;
  movieCasts: MovieCast[];
}
