export class LoginService {
  public async verifyUser(email: string, password: string): Promise<boolean> {
    const url: string = `http://localhost:8080/user/verify`
    const requestBody = {
      email: email,
      password: password
    };

    try {
      // Send login request to backend
      const response: Response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json"
        },
        body: JSON.stringify(requestBody)
      })

      // Handle unsuccessful response
      if (!response.ok) {
        if (response.status === 401) {
          return false; // Unauthorized - invalid credentials
        }
        throw new Error("Request failed: " + response.statusText);
      }

      const data = await response.json();

      // Check if backend returned JWT token
      if (data.success && data.token && data.user) {
        // Store JWT token in sessionStorage
        sessionStorage.setItem('authToken', data.token)
        sessionStorage.setItem('userData', JSON.stringify(data.user))
        return true;
      }

      return false;
    }
    catch (error) {
      console.error('Login error:', error);
      throw error;
    }
  }
}
