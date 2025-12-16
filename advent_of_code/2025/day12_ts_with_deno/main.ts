type Present = {
  num: number;
  shape: string[];
  area?: number;
};

type Requirement = {
  size: { x: number; y: number };
  area?: number;
  specifications: number[];
};

function solve(filename: string): number {
  const data = Deno.readTextFileSync(filename).trim();
  const cdata = data.split(/\n\n/g);
  const rdata = cdata.splice(-1);

  const presents: Present[] = [];

  for (const line of cdata) {
    const splitline = line.split(/:/g);
    const num = parseInt(splitline[0]);
    const shape = splitline[1].trim().split(/\n/g);
    presents.push({ num: num, shape: shape });
  }

  console.table(presents);

  const requirements: Requirement[] = [];
  const lines = rdata[0].split(/\n/g);

  console.table(lines);

  for (const line of lines) {
    const lineSplit = line.split(/:\s/g);
    const size = lineSplit[0];
    const splitSize = size.split("x");
    const requirement: Requirement = {
      size: {
        x: parseInt(splitSize[0]),
        y: parseInt(splitSize[1]),
      },
      specifications: lineSplit[1].split(/\s/g).map(Number),
    };
    requirements.push(requirement);
  }

  console.table(requirements);

  requirements.forEach((requirement) => {
    requirement.area = requirement.size.x * requirement.size.y;
  });

  presents.forEach((present) => {
    present.area = present.shape.join("").matchAll(/#/g).toArray().length;
  });

  let fitting = 0;

  requirements.forEach((requirement) => {
    let occupiedArea = 0;
    for (let i = 0; i < requirement.specifications.length; i++) {
      occupiedArea += requirement.specifications[i] * presents[i].area!;
    }
    if (occupiedArea <= requirement.area!) {
      fitting++;
    }
  });
  return fitting;
}

if (import.meta.main) {
  console.log(`Solution for part 1: ${solve("./data/input.txt")}`); // 593 with my input data
}
