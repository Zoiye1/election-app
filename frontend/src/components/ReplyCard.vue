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

</template>
