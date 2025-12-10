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

    return await response.json()
  }

  public async getRepliesByDiscussionId(discussionId: number): Promise<ReplyResponseDTO[]> {
    const response: Response = await fetch(`${this.baseUrl}/discussion/${discussionId}`, {
      method: 'GET',
      headers: {
        'Accept': 'application/json'
      }
    })

    if (!response.ok) {
      throw new Error('Request failed: ' + response.statusText)
    }

    return await response.json()
  }

  public async deleteReply(id: number) {
    const response: Response = await fetch(`${this.baseUrl}/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json'
        }
    })

    if (!response.ok) {
      throw new Error('Request failed: ' + response.statusText)
    }
  }
}

