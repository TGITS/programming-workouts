const COLORS = ["black","brown","red","orange","yellow","green","blue","violet","grey","white"]
const colorCode = color =>  { 
    for(var i = 0; i < COLORS.length; i++) {
        if(color === COLORS[i]) { return i }
    }
}

export {colorCode, COLORS}