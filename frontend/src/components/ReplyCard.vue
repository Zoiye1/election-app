<script setup lang="ts">

import type { ReplyResponseDTO } from '@/interfaces/Replies.ts'
import { computed } from 'vue'

const props = defineProps<{
  reply: ReplyResponseDTO
}>()

const emit = defineEmits<{
  (e: 'delete', id: number): void
}>()


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
    </div>

  </div>
</template>
