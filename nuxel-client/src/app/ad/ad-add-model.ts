
export interface AdModel{
    id: String
    name: String,
    description: String,
    price: number,
    city: String,
    region: String,
    postCode: number,
    phoneNumber: String,
    files: File[],
    userId: String,
    category: String
}