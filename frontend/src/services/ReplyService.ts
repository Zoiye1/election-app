import type { CreateReplyRequest, ReplyResponseDTO } from "@/interfaces/Replies";

export class ReplyService {
  private baseUrl: string = 'http://localhost:8080/api/v1/replies'

  public async createReply(data: CreateReplyRequest): Promise <ReplyResponseDTO>{

    const response: Response = await fetch(this.baseUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
        },
      body: JSON.stringify(data)
    })

    if (!response.ok) {
      throw new Error('request failed: ' + response.statusText)
    }
  }
}
