export interface RegisterRequest {
  username: string;
  password: string;
  email: string;
}

export interface RegisterResponse {
  id: number;
  username: string;
  password: string;
  email: string;
}

export class RegisterService {
  public async registerUser(username: string, email: string, password: string): Promise<RegisterResponse> {
    const url: string = `http://localhost:8080/user`
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
