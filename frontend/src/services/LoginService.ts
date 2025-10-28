export class LoginService {
  public async verifyUser(email: string, password: string): Promise<boolean> {
    const url: string = `http://localhost:8080/user/verify`
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
      })

      if (!response.ok) {
        throw new Error("Request failed: " + response.statusText);
      }

      const isValid = await response.json();

      // Als login succesvol is, sla user data op in sessionStorage
      if (isValid) {
        // Generate simple token (in productie zou backend dit moeten geven)
        const token = btoa(`${email}:${Date.now()}`)

        // Sla user data op
        const userData = {
          email: email,
          username: email.split('@')[0], // Extract username from email
          loginTime: new Date().toISOString()
        }

        sessionStorage.setItem('authToken', token)
        sessionStorage.setItem('userData', JSON.stringify(userData))
      }

      return isValid;
    }
    catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }
}
