const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export class LoginService {
  public async verifyUser(email: string, password: string): Promise<boolean> {
    const url: string = `${API_BASE_URL}/user/verify`;
    const requestBody = {
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
        if (response.status === 401) {
          return false;
        }
        if (response.status === 403) {
          const errorData = await response.json();
          throw new Error('EMAIL_NOT_VERIFIED: ' + (errorData.message || 'Please verify your email'));
        }
        throw new Error("Request failed: " + response.statusText);
      }

      const data = await response.json();

      if (data.success && data.token && data.user) {
        sessionStorage.setItem('authToken', data.token);
        sessionStorage.setItem('userData', JSON.stringify(data.user));
        return true;
      }

      return false;
    } catch (error) {
      console.error('Login error:', error);
      throw error;
    }
  }
}
