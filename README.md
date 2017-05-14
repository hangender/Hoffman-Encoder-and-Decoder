## Synopsis

Uses Binary Hoffman tree to encode lines of integers into none-prefix property 0's/1's codes for data compression. On reciever's end, the decoder reads in the compressed, binary data and outputs the orginal input contents.

Encoder:

input <- sample_input.txt

output -> encoded.bin * code_table.txt

Decoder:

input <- encoded.bin * code_table.txt

output -> decoded.txt (sample_input.txt)

## Using the binaries

java -jar encoder.jar sample_input.txt

the above command will produce 2 files.


java -jar decoder.jar encoded.bin code_table.txt

the above command will produce 1 file (decodes the binary file using provided code_table.txt)
