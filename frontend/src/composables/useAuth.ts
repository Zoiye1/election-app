import { ref, computed } from 'vue'

// Global reactive state
const currentUser = ref<any>(null)
const isAuthenticated = ref(false)

export function useAuth() {
  // Check if user is logged in
  const checkAuth = () => {
    const token = sessionStorage.getItem('authToken')
    const userData = sessionStorage.getItem('userData')

    if (token && userData) {
      try {
        currentUser.value = JSON.parse(userData)
        isAuthenticated.value = true
        return true
      } catch (error) {
        console.error('Failed to parse user data:', error)
        logout()
        return false
      }
    }
    return false
  }

  // Logout function
  const logout = () => {
    sessionStorage.removeItem('authToken')
    sessionStorage.removeItem('userData')
    currentUser.value = null
    isAuthenticated.value = false
  }

  // Get current auth status without side effects
  const getAuthStatus = () => {
    const token = sessionStorage.getItem('authToken')
    const userData = sessionStorage.getItem('userData')
    return !!(token && userData)
  }

  // Computed properties
  const userName = computed(() => {
    return currentUser.value?.username || currentUser.value?.email?.split('@')[0] || 'User'
  })

  const userEmail = computed(() => {
    return currentUser.value?.email || ''
  })

  return {
    currentUser,
    isAuthenticated,
    userName,
    userEmail,
    checkAuth,
    logout,
    getAuthStatus
  }
}
