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

// Generate user initials from username
const userInitials = computed(() => {
  const username = props.reply.authorName
  const words = username.split(' ')
  if (words.length >= 2) {
    return words[0][0].toUpperCase() + words[1][0].toUpperCase()
  }
  return username.substring(0, 2).toUpperCase()
})
</script>

<template>

</template>
