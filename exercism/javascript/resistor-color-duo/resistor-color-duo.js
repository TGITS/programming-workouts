const COLORS = ["black","brown","red","orange","yellow","green","blue","violet","grey","white"]
const colorCode = color =>  { 
    for(var i = 0; i < COLORS.length; i++) {
        if(color === COLORS[i]) { return i.toString() }
    }
}

export const value = ([color_1, color_2]) => {
  return Number(colorCode(color_1.toLowerCase()) + colorCode(color_2.toLowerCase()));
};
