export interface ReplyResponseDTO {
  id: number
  content: string
  authorName: string
  createdAt: string
  discussionId: number
  parentReplyId: number | null
}

export interface CreateReplyRequest {
  content: string
  discussionId: number
  userId: number
  parentReplyId?: number | null
}
