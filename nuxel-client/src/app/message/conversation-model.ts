import { MessageModel } from "./message-model";

export interface ConversationModel {
    buyerId: string,
    sellerId: string,
    messages: MessageModel[],
    adId: string,
    adName: String,
    adImage: String,
    id: string,
    senderName: string
}