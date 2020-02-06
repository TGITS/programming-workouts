export class ArmstrongNumbers {
    public static isArmstrongNumber = (num: number): boolean => {
        const value:string = String(num);
        const numberOfDigits:number = value.length;
        
        if (num > 0 && num < 10) {
            return true;
        }
        return false
    }
}