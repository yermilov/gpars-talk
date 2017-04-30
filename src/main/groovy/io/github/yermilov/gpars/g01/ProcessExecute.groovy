package io.github.yermilov.gpars.g01

def process = (['git', 'status']).execute([], new File('.'))
def processOutput = new StringWriter()
process.consumeProcessOutput processOutput, processOutput
process.waitFor()
println processOutput.toString().trim()