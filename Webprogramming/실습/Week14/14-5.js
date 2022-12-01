class Rectangle{
    constructor(height, width) {
        this.height = height;
        this.width = width;
    }
    //Getter
    get area(){
        return this.calcArea();
    }
    calcArea(){
        return this.height * this.width;
    }
}
const square1 = new Rectangle(5, 5);
const square2 = new Rectangle(10, 10);

console.log(square1.area);
console.log(square2.area);