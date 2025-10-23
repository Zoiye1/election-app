<script setup lang="ts">
import { computed } from 'vue'

interface Discussion {
  id: number
  title: string
  content: string
  author: {
    id: number
    username: string
  }
  createdAt: string
  views: number
  replies: number
}

const props = defineProps<{
  discussion: Discussion
}>()

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
  const username = props.discussion.author.username
  const words = username.split(' ')
  if (words.length >= 2) {
    return words[0][0].toUpperCase() + words[1][0].toUpperCase()
  }
  return username.substring(0, 2).toUpperCase()
})

// Generate avatar color based on user ID for consistency
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
  return colors[props.discussion.author.id % colors.length]
})

// Generate some sample tags based on content keywords (you can modify this logic)
const generateTags = computed(() => {
  const content = props.discussion.content.toLowerCase()
  const title = props.discussion.title.toLowerCase()
  const text = content + ' ' + title

  const tagMap = {
    'klimaat': 'Klimaat',
    'milieu': 'Milieu',
    'onderwijs': 'Onderwijs',
    'zorg': 'Zorg',
    'wonen': 'Wonen',
    'verkiezingen': 'Verkiezingen',
    'politiek': 'Politiek',
    'economie': 'Economie',
    'beleid': 'Beleid'
  }

  const foundTags = []
  for (const [keyword, tag] of Object.entries(tagMap)) {
    if (text.includes(keyword) && foundTags.length < 3) {
      foundTags.push(tag)
    }
  }

  return foundTags
})

// Truncate content for preview
const contentPreview = computed(() => {
  if (props.discussion.content.length > 200) {
    return props.discussion.content.substring(0, 200) + '...'
  }
  return props.discussion.content
})
</script>

<template>
  <div class="bg-white rounded-2xl p-6 shadow-lg hover:-translate-y-1 hover:shadow-2xl transition-all duration-300 cursor-pointer">
    <!-- Discussion Header -->
    <div class="flex items-center gap-4 mb-4">
      <div :class="[
        'w-12 h-12 rounded-full flex items-center justify-center text-white font-bold text-lg',
        avatarClass
      ]">
        {{ userInitials }}
      </div>
      <div>
        <h3 class="text-lg font-semibold text-gray-800">{{ discussion.author.username }}</h3>
        <p class="text-sm text-gray-500">Gestart op {{ formatDate(discussion.createdAt) }}</p>
      </div>
    </div>

    <!-- Discussion Content -->
    <div class="mb-5">
      <h2 class="text-xl font-semibold text-gray-800 mb-3">{{ discussion.title }}</h2>
      <p class="text-gray-600 leading-relaxed mb-4">{{ contentPreview }}</p>

      <!-- Tags (if any are generated) -->
      <div v-if="generateTags.length > 0" class="flex flex-wrap gap-2 mb-4">
        <span
          v-for="tag in generateTags"
          :key="tag"
          class="px-3 py-1 bg-gradient-to-r from-indigo-500 to-purple-600 text-white text-xs font-medium rounded-full"
        >
          {{ tag }}
        </span>
      </div>
    </div>

    <!-- Discussion Stats -->
    <div class="flex items-center justify-between pt-5 border-t border-gray-100">
      <div class="flex gap-5 text-sm text-gray-500">
        <span class="flex items-center gap-1">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
          </svg>
          {{ discussion.views }} weergaven
        </span>
        <span class="flex items-center gap-1">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z" />
          </svg>
          {{ discussion.replies }} reacties
        </span>
      </div>
      <button class="px-5 py-2 bg-gradient-to-r from-blue-500 to-green-500 text-white font-semibold text-sm rounded-full hover:-translate-y-0.5 hover:shadow-lg transition-all duration-300">
        Meer lezen
      </button>
    </div>
  </div>
</template>
