<script setup lang="ts">

import type { ReplyResponseDTO } from '@/interfaces/Replies.ts'
import { computed, ref } from 'vue'



const confirmDelete = () => {
  showDeleteModal.value = true
}

const cancelDelete = () => {
  showDeleteModal.value = false
}

const doDelete = () => {
  emit('delete', props.reply.id)
  showDeleteModal.value = false
}


const props = defineProps<{
  reply: ReplyResponseDTO
  currentUserId?: number
}>()

const emit = defineEmits<{
  (e: 'delete', id: number): void
  (e: 'reply', id: number): void
  (e: 'edit', id: number): void
}>()

const showDeleteModal = ref(false)

// Formats date as: 10 December 2025, 18:00
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

// Generate user initials from username(got it from discussion card)
const userInitials = computed(() => {
  const username = props.reply.authorName
  const words = username.split(' ')
  if (words.length >= 2) {
    return words[0][0].toUpperCase() + words[1][0].toUpperCase()
  }
  return username.substring(0, 2).toUpperCase()
})

// colours for the profile pictrures(got it from Discussion card)
const avatarClass = computed(() => {
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
  return colors[props.reply.authorId % colors.length]
})

// check if the reply is nested(nested means if it is a reply on a reply!)
const isNestedReply = computed(() => {
  return props.reply.parentReplyId !== null
})

</script>

<template>
  <div
    :class="[
      'bg-gray-50 rounded-lg p-4 shadow-sm transition-all duration-300',
      isNestedReply ? 'ml-8 border-l-4 border-purple-300' : ''
    ]"
  >
    <!-- Reply Header -->
    <div class="flex items-center justify-between mb-2">
      <div class="flex items-center gap-2">
        <div :class="[
          'w-8 h-8 rounded-full flex items-center justify-center text-white font-bold text-xs',
          avatarClass
        ]">
          {{ userInitials }}
        </div>
        <div>
          <span class="font-medium text-gray-800 text-sm">{{ reply.authorName }}</span>
          <span class="text-xs text-gray-400 ml-2">{{ formatDate(reply.createdAt) }}</span>
        </div>
      </div>

      <!-- Buttons wrapper -->
      <div class="flex items-center gap-3">
        <!-- Edit Button -->
        <button
          v-if="currentUserId === reply.authorId"
          @click="emit('edit', props.reply.id)"
          class="text-gray-400 hover:text-blue-500 transition-colors"
          title="Bewerk reactie"
        >
          <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
          </svg>
        </button>

        <!-- Delete Button -->
        <button
          v-if="currentUserId === reply.authorId"
          @click="confirmDelete"
          class="text-gray-400 hover:text-red-500 transition-colors"
          title="Verwijder reactie"
        >
          <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
          </svg>
        </button>

        <!-- Reply Button -->
        <button
          @click="emit('reply', props.reply.id)"
          class="text-purple-500 hover:text-purple-700 transition-colors text-sm font-medium"
        >
          Reageer
        </button>
      </div>
    </div>

    <p class="text-gray-600 text-sm leading-relaxed ml-10 whitespace-pre-wrap break-words">{{ reply.content }}</p>

    <!-- Delete Modal -->
    <div v-if="showDeleteModal" class="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div class="bg-white rounded-xl p-6 max-w-sm mx-4 shadow-2xl">
        <h3 class="text-lg font-semibold text-gray-800 mb-2">Reactie verwijderen?</h3>
        <p class="text-gray-600 text-sm mb-6">Weet je zeker dat je deze reactie wilt verwijderen? Dit kan niet ongedaan worden.</p>
        <div class="flex gap-3">
          <button
            @click="cancelDelete"
            class="flex-1 px-4 py-2 border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-50 transition-colors"
          >
            Annuleren
          </button>
          <button
            @click="doDelete"
            class="flex-1 px-4 py-2 bg-red-500 text-white rounded-lg hover:bg-red-600 transition-colors"
          >
            Verwijderen
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
