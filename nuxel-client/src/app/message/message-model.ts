export interface MessageModel{
    description: string,
    senderId: string,
    conversationId: string,
    timeSent: Date,
    id: string,
    unread: boolean
}