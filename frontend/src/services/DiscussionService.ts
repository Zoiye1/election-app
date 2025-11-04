export interface Discussion {
  id: number
  title: string
  content: string
  author: {
    id: number
    username: string
  }
  createdAt: string
  views: number
  replies: number
}

export interface DiscussionResponseDTO {
  id: number
  title: string
  content: string
  author: {
    id: number
    username: string
  }
  createdAt: string
  views: number
  replies: number
}

export interface CreateDiscussionRequest {
  title: string
  content: string
  author: {
    id: number
  }
}

export interface UpdateDiscussionRequest {
  title?: string
  content?: string
}

export class DiscussionService {
  private baseUrl: string = 'http://localhost:8080/discussion'

  /**
   * Get all discussions from the API
   */
  public async getAllDiscussions(): Promise<Discussion[]> {
    try {
      const response: Response = await fetch(this.baseUrl, {
        method: 'GET',
        headers: {
          'Accept': 'application/json'
        }
      })

      if (!response.ok) {
        throw new Error('Request failed: ' + response.statusText)
      }

      return await response.json()
    } catch (error) {
      console.error('Fetch error:', error)
      throw error
    }
  }

  /**
   * Get a single discussion by ID
   */
  public async getDiscussionById(id: number): Promise<Discussion> {
    const url: string = `${this.baseUrl}/${id}`

    try {
      const response: Response = await fetch(url, {
        method: 'GET',
        headers: {
          'Accept': 'application/json'
        }
      })

      if (!response.ok) {
        throw new Error('Request failed: ' + response.statusText)
      }

      return await response.json()
    } catch (error) {
      console.error('Fetch error:', error)
      throw error
    }
  }

  /**
   * Create a new discussion
   */
  public async createDiscussion(data: CreateDiscussionRequest): Promise<DiscussionResponseDTO> {
    try {
      const response: Response = await fetch(this.baseUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
        },
        body: JSON.stringify(data)
      })

      if (!response.ok) {
        throw new Error('Request failed: ' + response.statusText)
      }

      return await response.json()
    } catch (error) {
      console.error('Fetch error:', error)
      throw error
    }
  }

  /**
   * Delete a discussion by ID
   */
  public async deleteDiscussion(id: number): Promise<void> {
    const url: string = `${this.baseUrl}/${id}`

    try {
      const response: Response = await fetch(url, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json'
        }
      })

      if (!response.ok) {
        throw new Error('Request failed: ' + response.statusText)
      }
    } catch (error) {
      console.error('Fetch error:', error)
      throw error
    }
  }

  public async updateDiscussion(id: number, data: UpdateDiscussionRequest): Promise<Discussion> {
    const url: string = `${this.baseUrl}/${id}`

    try {
      const response: Response = await fetch(url, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
        },
        body: JSON.stringify(data)
      })

      if (!response.ok) {
        throw new Error('Request failed: ' + response.statusText)
      }

      return await response.json()
    } catch (error) {
      console.error('Fetch error:', error)
      throw error
    }
  }
}
