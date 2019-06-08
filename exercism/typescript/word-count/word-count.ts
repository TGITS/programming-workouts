class Words {
    count(sentence: string) {
        const words = sentence.trim().split(/\s+/)
        const counter = new Map<string, number>()
        words.map(elt=>elt.toLowerCase()).forEach(elt => counter.set(elt, counter.has(elt) ? <number>counter.get(elt) + 1: 1))
        return counter
    }
  }
  
  export default Words