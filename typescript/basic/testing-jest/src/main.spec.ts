import { testingJest } from './main'

test('should return false given external link', () => {
  expect(testingJest('https://google.com')).toBe(false)
})

test('should return true given internal link', () => {
  expect(testingJest('/some-page')).toBe(true)
})