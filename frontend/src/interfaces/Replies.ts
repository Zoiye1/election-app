export interface ReplyResponseDTO {
  id: number
  content: string
  authorName: string
  authorId: number
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
