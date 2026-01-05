export interface RegisterRequest {
  username: string;
  password: string;
  email: string;
}

export interface RegisterResponse {
  success: boolean;
  message: string;
  user: {
    id: number;
    username: string;
    email: string;
    password: string;
  };
}

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export class RegisterService {
  public async registerUser(username: string, email: string, password: string): Promise<RegisterResponse> {
    const url = `${API_BASE_URL}/user`;
    const requestBody: RegisterRequest = {
      username: username,
      email: email,
      password: password
    };

    try {
      const response: Response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json"
        },
        body: JSON.stringify(requestBody)
      });

      if (!response.ok) {
        throw new Error("Request failed: " + response.statusText);
      }

      return await response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }
}
