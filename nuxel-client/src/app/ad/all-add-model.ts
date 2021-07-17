import { ConversationModel } from "../message/conversation-model";

export interface AllAdModel{
    id: String,
    name: String,
    description: String,
    price: number,
    date: String,
    address: any
    postCode: number,
    phoneNumber: String,
    userId: String
    images: String[],
    category: any,
    conversations: ConversationModel[]
}