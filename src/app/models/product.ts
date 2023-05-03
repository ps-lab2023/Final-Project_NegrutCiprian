export class Product {
  constructor(
    public name: string,
    public identifier: string,
    public description: string,
    public category: string,
    public price: number,
    public externalId: string,
    public image: String | null,
    public promo: string,
    public outOfStock?: boolean
){}
}
