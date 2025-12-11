import type { CreateReplyRequest, ReplyResponseDTO } from "@/interfaces/Replies";

export class ReplyService {
  private baseUrl: string = 'http://localhost:8080/api/v1/replies'

  public async createReply(data: CreateReplyRequest): Promise <ReplyResponseDTO>{
    try {
      // get token from storage
      const token = sessionStorage.getItem('authToken');

      // if not logged in give error
      if (!token) {
        throw new Error('Not authenticated - please login');
      }
    const response: Response = await fetch(this.baseUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': `Bearer ${token}`
        },
      body: JSON.stringify(data)
    })

      if (response.status === 401) {
        // Token invalid or expired - redirect to login
        window.location.href = '/login';
        throw new Error('Unauthorized - please login again');
      }

    if (!response.ok) {
      throw new Error('request failed: ' + response.statusText)
    }

    return await response.json()
      }catch (error) {
      console.error('Fetch error:', error)
      throw error
    }
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
    try {

      // get token from storage
      const token = sessionStorage.getItem('authToken');

      // if not logged in give error
      if (!token) {
        throw new Error('Not authenticated - please login');
      }

    const response: Response = await fetch(`${this.baseUrl}/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Authorization': `Bearer ${token}`
        }
    })
      // session expired, redirect user to login page
      if (response.status === 401) {
        window.location.href = '/login';
        throw new Error('Unauthorized - please login again');
      }

      // if user tries to delete someone elses comment
      if (response.status === 403) {
        throw new Error('A user can only delete their own replies!');
      }

    if (!response.ok) {
      throw new Error('Request failed: ' + response.statusText)
    }

    } catch (error) {
      console.error('Delete error:', error)
      throw error
    }
  }
}

