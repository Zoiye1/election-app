<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import Navbar from '../components/Navbar.vue'
import { DiscussionService, type DiscussionResponseDTO } from '@/services/DiscussionService'
import { computed } from 'vue'
import { ReplyService } from '@/services/ReplyService.ts'
import type { ReplyResponseDTO } from '@/interfaces/Replies.ts'
import ReplyCard from '@/components/ReplyCard.vue'
import { useAuth } from '@/composables/useAuth.ts'

// Initialize router and service
const route = useRoute()
const router = useRouter()
const discussionService = new DiscussionService()

// State
const discussionId = route.params.id
const loading = ref(true)
const error = ref('')
const replyError = ref('')
const discussion = ref<DiscussionResponseDTO | null>(null)
// State for replies
const replyService = new ReplyService()
const replies = ref<ReplyResponseDTO[]>([])
const newReplyContent = ref('')
const submitting = ref(false)
const replyingTo = ref<number | null>(null)
const replyingToAuthor = ref<string>('')

// UserAuthentication
const { currentUser, checkAuth } = useAuth()

// Fetch discussions from API
const fetchDiscussion = async () => {
  try {
    discussion.value = await discussionService.getDiscussionById(Number(discussionId))
  } catch (err) {
    error.value = 'Could not load post'
    console.error('Fetch discussions error:', err)
  } finally {
    loading.value = false
  }
}

// Fetch replies from API
const fetchReplies = async () => {
  try {
    replies.value = await replyService.getRepliesByDiscussionId(Number(discussionId))
  } catch (err) {
    console.error('Fetch replies error:', err)
  }
}



// Delete reply trough API
const handleDeleteReply = async (id: number) => {
  try {
    await replyService.deleteReply(id)
    replies.value = replies.value.filter(r => r.id !== id)
  } catch (err) {
    console.error('Delete reply error:', err)
  }
}

// Create reply trough API
const handleCreateReply = async () => {
  replyError.value = ''

  // check if user is logged in
  if (!currentUser.value) {
    replyError.value = 'Je moet ingelogd zijn om te reageren'
    newReplyContent.value = ''
    return
  }

  if (!newReplyContent.value.trim()) return

  submitting.value = true
  try {
    const newReply = await replyService.createReply({
      content: newReplyContent.value,
      discussionId: Number(discussionId),
      parentReplyId: replyingTo.value
    })
    replies.value.push(newReply)
    newReplyContent.value = ''
    replyingTo.value = null  // ← reset
    replyingToAuthor.value = ''
  } catch (err) {
    replyError.value = 'Er ging iets mis, probeer het opnieuw.'
    console.error('create reply error: ', err)
  } finally {
    submitting.value = false
  }
}

const handleReplyTo = (replyId: number) => {
  const reply = replies.value.find(r => r.id === replyId)
  if (reply) {
    replyingTo.value = replyId
    replyingToAuthor.value = reply.authorName
  }
}

onMounted(() => {
  checkAuth()
  fetchDiscussion()
  fetchReplies()
  const particlesContainer = document.getElementById('particles')
  if (particlesContainer) {
    for (let i = 0; i < 50; i++) {
      const particle = document.createElement('div')
      particle.className = 'absolute w-1 h-1 bg-white/30 rounded-full'
      particle.style.left = Math.random() * 100 + '%'
      particle.style.top = Math.random() * 100 + '%'
      particle.style.animation = `float ${6 + Math.random() * 3}s ease-in-out infinite`
      particle.style.animationDelay = Math.random() * 6 + 's'
      particlesContainer.appendChild(particle)
    }
  }
})

const formatDate = (dateString: string) => {
  const date = new Date(dateString)
  return date.toLocaleDateString('nl-NL', {
    day: '2-digit',
    month: 'long',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  })
}

const userInitials = computed(() => {
  if (!discussion.value) return ''
  const username = discussion.value.authorName
  const words = username.split(' ')
  if (words.length >= 2) {
    return words[0][0].toUpperCase() + words[1][0].toUpperCase()
  }
  return username.substring(0, 2).toUpperCase()
})

const avatarClass = computed(() => {
  if (!discussion.value) return ''
  const colors = [
    'bg-gradient-to-br from-cyan-400 to-blue-600',
    'bg-gradient-to-br from-pink-400 to-purple-500',
    'bg-gradient-to-br from-emerald-400 to-cyan-500',
    'bg-gradient-to-br from-yellow-400 to-orange-500',
    'bg-gradient-to-br from-indigo-500 to-purple-600',
    'bg-gradient-to-br from-green-400 to-blue-500',
    'bg-gradient-to-br from-red-400 to-pink-500',
    'bg-gradient-to-br from-purple-400 to-indigo-500'
  ]
  return colors[discussion.value.authorId % colors.length]
})
/**
 * this method gets all replies without a parent id.
 * then it gets all child items per parentReply.
 * and then it returns a beautiful list of parent items with al child items sorted under their parent item.
 */
const sortedReplies = computed(() => {
  const topLevel = replies.value.filter(r => r.parentReplyId === null)
  const result: ReplyResponseDTO[] = []

  topLevel.forEach(parent => {
    result.push(parent)
    const children = replies.value.filter(r => r.parentReplyId === parent.id)
    result.push(...children)
  })

  return result
})

</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-[#667eea] to-[#764ba2] relative overflow-hidden">
    <div class="fixed inset-0 pointer-events-none" id="particles"></div>
    <Navbar />
    <main class="max-w-6xl mx-auto px-4 py-10 md:py-24">
      <!-- Loading State -->
      <div v-if="loading" class="text-center py-16">
        <div class="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-white"></div>
        <p class="text-white text-lg mt-4">Bericht laden...</p>
      </div>

      <!-- Error State -->
      <div v-else-if="error" class="text-center py-16">
        <div class="bg-red-100 border border-red-300 rounded-2xl p-8 max-w-md mx-auto">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-12 w-12 text-red-500 mx-auto mb-4"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-2.5L13.732 4c-.77-.833-1.964-.833-2.732 0L3.732 16.5c-.77.833.192 2.5 1.732 2.5z"
            />
          </svg>
          <p class="text-red-700 text-lg mb-4">{{ error }}</p>
          <button
            @click="fetchDiscussion"
            class="px-6 py-3 bg-red-600 text-white font-semibold rounded-full hover:bg-red-700 hover:-translate-y-0.5 transition-all duration-300"
          >
            Opnieuw proberen
          </button>
        </div>
      </div>

      <!-- Discussion Content -->
      <div v-else-if="discussion" class="space-y-6">
        <!-- Back Button -->
        <button
          @click="router.push('/discussion')"
          class="inline-flex items-center gap-2 text-white hover:text-gray-200 transition-colors"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-5 w-5"
            viewBox="0 0 20 20"
            fill="currentColor"
          >
            <path
              fill-rule="evenodd"
              d="M9.707 16.707a1 1 0 01-1.414 0l-6-6a1 1 0 010-1.414l6-6a1 1 0 011.414 1.414L5.414 9H17a1 1 0 110 2H5.414l4.293 4.293a1 1 0 010 1.414z"
              clip-rule="evenodd"
            />
          </svg>
          Terug naar discussies
        </button>

        <!-- Main Discussion Card -->
        <div class="bg-white rounded-2xl p-8 shadow-2xl">
          <!-- Author Header -->
          <div class="flex items-center gap-4 mb-6 pb-6 border-b border-gray-200">
            <div
              :class="[
                'w-16 h-16 rounded-full flex items-center justify-center text-white font-bold text-2xl',
                avatarClass,
              ]"
            >
              {{ userInitials }}
            </div>
            <div>
              <h3 class="text-xl font-semibold text-gray-800">{{ discussion.authorName }}</h3>
              <p class="text-sm text-gray-500">Gestart op {{ formatDate(discussion.createdAt) }}</p>
            </div>
          </div>

          <!-- Discussion Title -->
          <h1 class="text-4xl font-bold text-gray-800 mb-6">
            {{ discussion.title }}
          </h1>

          <!-- Discussion Content -->
          <div class="prose prose-lg max-w-none mb-8">
            <p class="text-gray-700 leading-relaxed whitespace-pre-wrap">
              {{ discussion.content }}
            </p>
          </div>

          <!-- Discussion Stats -->
          <div class="flex items-center gap-6 pt-6 border-t border-gray-200 text-sm text-gray-500">
            <span class="flex items-center gap-2">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-5 w-5"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
                />
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"
                />
              </svg>
              {{ discussion.views }} weergaven
            </span>
            <span class="flex items-center gap-2">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-5 w-5"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z"
                />
              </svg>
              {{ discussion.replies }} reacties
            </span>
          </div>
      </div>

        <!-- Replies Section -->
        <div class="mt-8">
          <h2 class="text-white text-2xl font-bold mb-4">
            Reacties ({{ replies.length }})
          </h2>

          <!-- Reply Input -->
          <div class="bg-white rounded-xl p-4 shadow-md mb-4 flex gap-3 items-center">
            <!-- reply error -->
            <!-- Replying to indicator -->
            <div v-if="replyingTo" class="flex items-center justify-between mb-2 text-sm text-purple-600">
              <span>Reageren op {{ replyingToAuthor }}</span>
              <button @click="replyingTo = null; replyingToAuthor = ''" class="text-gray-400 hover:text-gray-600">
                ✕
              </button>
            </div>

            <textarea
              v-model="newReplyContent"
              :placeholder="replyError || 'Schrijf een reactie...'"
              :class="[
    'flex-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500 resize-none',
    replyError ? 'border-red-500 placeholder-red-500' : 'border-gray-200'
  ]"
              :disabled="submitting"
              @keyup.enter.ctrl="handleCreateReply"
              @input="replyError = ''"
              rows="1"
            ></textarea>
            <button
              @click="handleCreateReply"
              :disabled="submitting || !newReplyContent.trim()"
              class="px-4 py-2 bg-gradient-to-r from-purple-500 to-indigo-600 text-white rounded-lg hover:opacity-90 disabled:opacity-50 transition-all"
            >
              <svg v-if="submitting" class="animate-spin h-5 w-5" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              <svg v-else xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                <path d="M10.894 2.553a1 1 0 00-1.788 0l-7 14a1 1 0 001.169 1.409l5-1.429A1 1 0 009 15.571V11a1 1 0 112 0v4.571a1 1 0 00.725.962l5 1.428a1 1 0 001.17-1.408l-7-14z" />
              </svg>
            </button>
          </div>

          <!-- Replies List -->
          <div class="space-y-4">
            <ReplyCard
              v-for="reply in sortedReplies"
              :key="reply.id"
              :reply="reply"
              :currentUserId="currentUser?.id"
              @delete="handleDeleteReply"
              @reply="handleReplyTo"
            />
          </div>

          <!-- Empty state -->
          <div v-if="replies.length === 0" class="bg-white/10 rounded-xl p-6 text-center">
            <p class="text-white/70">Nog geen reacties. Wees de eerste!</p>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>
