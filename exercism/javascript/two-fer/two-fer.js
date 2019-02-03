export const twoFer = (name) => {
   if (!name || 0 === name.length || !name.trim()) {
        name = 'you'
   } 
   return `One for ${name}, one for me.`
};